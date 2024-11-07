package Domain.Common.Service;

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
	public void plannerAdd(PlannerDto plannerDto) {
		
		
	}
}
