package Domain.Common.Dao.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.mysql.cj.jdbc.MysqlXADataSource;

public class ConnectionPool {
	private List<ConnectionItem> connectionPool;
	//ConnectionPool 에 저장될 Connection 을 위한 변수
	private final int size=10;
	private String url="jdbc:mysql://localhost:3306/bookDB";;
	private String id="root";
	private String pw="1234";

	//싱글톤 패턴 코드 추가
	private ConnectionPool() throws SQLException {
		
		this.connectionPool = new ArrayList();
		for(int i=0;i<size;i++) {
			
//			Connection conn = DriverManager.getConnection(url,id,pw);	
//			connectionPool.add(new ConnectionItem(conn,i));
			
			//TX
			//XA 트랜잭션을 처리할 수 있는 연결을 설정, 분산 트랜잭션을 다룸
	        MysqlXADataSource xaDataSource = new MysqlXADataSource(); // MySQL JDBC 드라이버에서 제공하는 클래스
	        
            xaDataSource.setUrl(url);
            xaDataSource.setUser(id);
            xaDataSource.setPassword(pw);
            XAConnection xaConnection = xaDataSource.getXAConnection(); //분산 트랜잭션을 처리할 수 있는 JDBC 연결 객체
            
            // XA 트랜잭션에 의해 관리되는 connection 객체 가져옴
           
            Connection conn = xaConnection.getConnection();
            
            //트랜잭션 관리를 위한 XAResource 객체를 가져옴
            XAResource xaResource = xaConnection.getXAResource();
            
            //ConnectionItem에 xaResource객체를 멤버로 포함
            connectionPool.add(new ConnectionItem(conn, xaResource));
		
		}
	
		System.out.println("[CP] Connection size : " + connectionPool.size());

	}
	private static ConnectionPool instance;
	public static ConnectionPool getInstance() throws SQLException {
		if(instance==null)
			instance = new ConnectionPool();
		return instance;
	}
	
	//
	public synchronized ConnectionItem getConnection() throws Exception {
		
		for(ConnectionItem connItem : connectionPool) {
			if(connItem.isUse()) {
				connItem.setUse(false); 	//사용중인 상태로 변경
				return connItem;			//Connection 리턴
			}
		}
		throw new Exception("모든 Connection이 사용중인 상태입니다.");
	}
	

	
	public synchronized void releaseConnection(ConnectionItem connItem) {
		connItem.setUse(true);
	}

	//--------------------------
	//분산 TX
	//--------------------------
    // 트랜잭션 시작
    public void beginTransaction() throws Exception {
        for (int i = 0; i < connectionPool.size(); i++) {
            ConnectionItem connItem = connectionPool.get(i);
            
            if (connItem.getInTransaction()) {
                // 이미 트랜잭션이 시작된 경우 무시
                continue;
            }
            //식별자생성
            Xid xid = createXid(100, i);
            connItem.setXid(xid);  // Xid 저장

            
            // XA 트랜잭션 시작
            connItem.getXaResource().start(xid, XAResource.TMNOFLAGS);
            connItem.setInTransaction(true); // 트랜잭션 활성화 플래그 설정
        }
    }
	
    // 트랜잭션 커밋
    //2-Phase Commit(2PC) 를 통한 트랜잭션 조정
    public void commitTransaction() throws Exception {
        List<Xid> xidList = new ArrayList<>();
        try {
            for (ConnectionItem connItem : connectionPool) {
                XAResource xaResource = connItem.getXaResource();
                Xid xid = connItem.getXid();
                xidList.add(xid);
                xaResource.end(xid, XAResource.TMSUCCESS);
            }

            for (ConnectionItem connItem : connectionPool) {
                XAResource xaResource = connItem.getXaResource();
                Xid xid = connItem.getXid();
                int prepare = xaResource.prepare(xid);
                if (prepare != XAResource.XA_OK) {
                    throw new Exception("Prepare failed for one or more resources.");
                }
            }

            for (int i = 0; i < connectionPool.size(); i++) {
                XAResource xaResource = connectionPool.get(i).getXaResource();
                Xid xid = xidList.get(i);
                xaResource.commit(xid, false);
                connectionPool.get(i).resetTransaction();  // 상태 초기화
            }

            System.out.println("Distributed transaction committed successfully.");
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }


    

    public void rollbackTransaction() throws Exception {
        for (ConnectionItem connItem : connectionPool) {
            XAResource xaResource = connItem.getXaResource();
            Xid xid = connItem.getXid();
            if (xid != null) {
                try {
                    xaResource.rollback(xid);
                } catch (Exception ex) {
                    System.out.println("Rollback failed for XID: " + xid + ". " + ex.getMessage());
                }
            }
            connItem.resetTransaction();  // 상태 초기화
        }
        System.out.println("Distributed transaction rolled back.");
    }
    
    //
    // Xid 생성 메서드
    private static Xid createXid(int bid, int tid) {
        return new Xid() {
            private int formatId = 1;
            private byte[] branchQualifier = new byte[] { (byte) tid, (byte) (System.currentTimeMillis() & 0xFF) };
            private byte[] globalTransactionId = new byte[] { (byte) bid, (byte) (System.nanoTime() & 0xFF) };

            @Override
            public int getFormatId() {
                return formatId;
            }

            @Override
            public byte[] getBranchQualifier() {
                return branchQualifier;
            }

            @Override
            public byte[] getGlobalTransactionId() {
                return globalTransactionId;
            }
        };
    }
    
	
}
