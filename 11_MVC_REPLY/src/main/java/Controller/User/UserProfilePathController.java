package Controller.User;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import properties.UploadProperties;


public class UserProfilePathController implements SubController{


	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	System.out.println("GET /profile/image");
		
		
		String username = req.getParameter("username");
		
		String path = UploadProperties.uploadPath
					+ File.separator
					+ UploadProperties.profilePath
					+ File.separator
					+ username
					+ File.separator;
		
		File file = new File(path + "profileImg.jpg");
		
	
		
		//프로필 미존재시 익명 이미지로 대체
		if(!file.exists()) {
			String projectPath = req.getServletPath();
			System.out.println("projectPath : " + projectPath);
			file = new File("C:\\Users\\Administrator\\Downloads\\새 폴더\\LEC\\07_JSP_SERVLET");
		}
	
		String mimeType = "application/octet-stream";
	
		resp.setContentType(mimeType);
		resp.setContentLengthLong(file.length());

		//스트림전송
		try {
			FileInputStream fin = new FileInputStream(file);
			ServletOutputStream sout = resp.getOutputStream();
			byte [] buff = new byte[1024];
			while(true) {
				int cnt = fin.read(buff);
				if(cnt==-1)
					break;
				sout.write(buff,0,cnt);
				sout.flush();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}


}
