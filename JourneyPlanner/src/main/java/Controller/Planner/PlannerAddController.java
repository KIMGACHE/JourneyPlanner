package Controller.Planner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.SubController;
import Domain.Common.Dto.PlannerDto;
import Domain.Common.Dto.UserDto;
import Domain.Common.Service.PlannerServiceImpl;

public class PlannerAddController implements SubController {
    // 플래너 관련 서비스 객체
    private PlannerServiceImpl plannerService;

    public PlannerAddController() {
        this.plannerService = PlannerServiceImpl.getInstance();
    }

    public void ExceptionHandler(Exception e, HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            // 발생한 예외를 요청 객체에 설정
            req.setAttribute("exception", e);
            // 에러 페이지로 포워딩
            req.getRequestDispatcher("/WEB-INF/view/planner/error.jsp").forward(req, resp);
        } catch (Exception ex) {
            // 포워딩 과정에서 예외가 발생할 경우 ServletException으로 래핑하여 던짐
            throw new ServletException(ex);
        }
    }
    
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 요청 메서드 확인 (GET 또는 POST)
            String method = req.getMethod();

            // GET 요청 처리
            if ("GET".equals(method)) {
                System.out.println("[BC] GET /planner/add..");
                // 플래너 추가 페이지로 이동
                req.getRequestDispatcher("/WEB-INF/view/planner/add.jsp").forward(req, resp);
                return;
            }

            // POST 요청 처리
            // 사용자 세션에서 로그인 상태 확인
            HttpSession session = req.getSession();
            if (session.getAttribute("userDto") == null) {
                // 로그인이 되어 있지 않으면 로그인 페이지로 리다이렉트
                resp.sendRedirect(req.getContextPath() + "/user/login");
                return;
            }

            // 세션에서 사용자 정보를 가져옴
            UserDto userDto = (UserDto) session.getAttribute("userDto");
            String userid = userDto.getUserid(); // 현재 로그인한 사용자의 ID

            // 클라이언트로부터 전달된 파라미터 수집
            Integer areacode = Integer.parseInt(req.getParameter("areacode")); // 지역 코드
            Integer citycode = Integer.parseInt(req.getParameter("citycode")); // 도시 코드
            LocalDate startdate = LocalDate.parse(req.getParameter("startdate"), DateTimeFormatter.ISO_LOCAL_DATE); // 시작 날짜
            LocalDate enddate = LocalDate.parse(req.getParameter("enddate"), DateTimeFormatter.ISO_LOCAL_DATE); // 종료 날짜

            // 수집한 데이터를 기반으로 PlannerDto 객체 생성
            PlannerDto plannerDto = new PlannerDto(0, areacode, citycode, startdate, enddate, userid);
            System.out.println(plannerDto); // 디버깅용: 생성된 PlannerDto 확인

            // 플래너 추가 서비스 실행
            Map<String, Object> rvalue = plannerService.plannerAdd(plannerDto); // 서비스 계층 호출
            Boolean isAdded = (Boolean) rvalue.get("isAdded"); // 추가 성공 여부 확인

            if (isAdded != null && isAdded) {
                // 플래너 추가가 성공한 경우
                System.out.println("뷰로이동");
                // 플래너 목록 페이지로 리다이렉트
                resp.sendRedirect(req.getContextPath() + "/planner/list");
                return;
            } else {
                // 플래너 추가가 실패한 경우 다시 플래너 추가 페이지로 포워딩
                req.getRequestDispatcher("/WEB-INF/view/planner/add.jsp").forward(req, resp);
                return;
            }

        } catch (Exception e) {
            // 예외 발생 시 ExceptionHandler 호출
            try {
                ExceptionHandler(e, req, resp);
            } catch (ServletException e1) {
                // 예외 핸들러 내부에서 예외가 발생할 경우 재처리
                try {
                    throw new ServletException(e1);
                } catch (Exception e2) {
                    e2.printStackTrace(); // 최후의 예외 처리
                }
            }
        }
    }
}
