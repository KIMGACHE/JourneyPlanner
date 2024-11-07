package Domain.Common.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.Common.Dao.PlannerDaoImpl;
import Domain.Common.Dto.PlannerDto;

public class PlannerServiceImpl {
	private static PlannerDaoImpl plannerDaoImpl;
	private static PlannerServiceImpl instance;
	
	public PlannerServiceImpl() {
		plannerDaoImpl = PlannerDaoImpl.getInstance();
	}
	
	public static PlannerServiceImpl getInstance() {
		if(instance==null) {
			instance = new PlannerServiceImpl();
		}
		
		return instance;
	}

	
	// CRUD
	// 플래너 작성
	public Map<String,Object> plannerAdd(PlannerDto plannerDto) {
		Map<String,Object> rvalue = new HashMap();
		try {			
			Boolean isAdded = plannerDaoImpl.insert(plannerDto);
			if(isAdded) {
				rvalue.put("isAdded", true);
				rvalue.put("message", "플래너를 추가하였습니다.");
			} else {
				rvalue.put("isAdded", false);
				rvalue.put("message", "플래너를 작성하는데 오류가 발생하였습니다.");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return rvalue;
	}
	
	// 플래너 수정
	public Map<String,Object> plannerUpdate(PlannerDto plannerDto) {
		Map<String,Object> rvalue = new HashMap();
		try {			
			Boolean isUpdated = plannerDaoImpl.update(plannerDto);
			if(isUpdated) {
				rvalue.put("isUpdated", true);
				rvalue.put("message", "플래너를 수정하였습니다.");
			} else {
				rvalue.put("isUpdated", false);
				rvalue.put("message", "플래너 수정에 실패하였습니다.");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return rvalue;
	}
	
	// 플래너 삭제
	public Map<String,Object> plannerDelete(int plannerid) {
		Map<String,Object> rvalue = new HashMap();
		try {			
			Boolean isDeleted = plannerDaoImpl.delete(plannerid);
			if(isDeleted) {
				rvalue.put("isDeleted", true);
				rvalue.put("message", "플래너를 삭제하였습니다.");
			} else {
				rvalue.put("isDeleted", false);
				rvalue.put("message", "플래너 삭제에 실패하였습니다.");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return rvalue;
	}

	public Map<String, Object> plannerList() {
		Map<String,Object> rvalue = new HashMap();
		try {
			List<PlannerDto> list = plannerDaoImpl.selectAll();
			if(list!=null) {
				rvalue.put("isSelected", true);
				rvalue.put("list", list);
				rvalue.put("message", "플래너를 전체 조회하였습니다.");
			} else {
				rvalue.put("isSelected", false);
				rvalue.put("message", "플래너 조회에 실패하였습니다.");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return rvalue;
	}
	
	// ??
}
