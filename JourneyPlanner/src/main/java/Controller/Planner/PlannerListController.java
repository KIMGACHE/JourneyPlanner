package Controller.Planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dto.PlannerDto;
import Domain.Common.Service.PlannerServiceImpl;

public class PlannerListController implements SubController {
    private PlannerServiceImpl plannerService; // PlannerService를 사용하여 비즈니스 로직 처리

    public PlannerListController() {
        this.plannerService = PlannerServiceImpl.getInstance(); // Singleton 패턴으로 생성된 PlannerServiceImpl 인스턴스를 사용
    }

    public void ExceptionHandler(Exception e, HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            req.setAttribute("exception", e); // 예외 정보를 요청 속성에 추가
            req.getRequestDispatcher("/WEB-INF/view/planner/error.jsp").forward(req, resp); // 에러 페이지로 이동
        } catch (Exception ex) {
            throw new ServletException(ex); // 추가 예외 발생 시 다시 던짐
        }
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 요청 메서드 확인
            String method = req.getMethod(); // 요청 메서드(GET, POST 등)를 확인
            if ("GET".equals(method)) {
                // GET 요청 처리 (일정 리스트 조회)
                System.out.println("[BC] GET /planner/list.."); // 로그 출력
                Map<String, Object> rvalue = plannerService.plannerList(); // PlannerService에서 리스트를 가져옴
                req.setAttribute("list", rvalue.get("list")); // 결과 리스트를 요청 속성에 추가
                req.getRequestDispatcher("/WEB-INF/view/planner/list.jsp").forward(req, resp); // 리스트 JSP로 이동
                return;
            }


        } catch (Exception e) {
            try {
                // 예외 발생 시 처리
                ExceptionHandler(e, req, resp);
            } catch (ServletException e1) {
                try {
                    // 추가 예외 처리
                    throw new ServletException(e1);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
