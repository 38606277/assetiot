package system.log;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system.configure.AppConstants;
import system.form.user.UserModel;

@RestController
@RequestMapping("/reportServer")
public class SysContext {

    private static ThreadLocal<UserModel> map = new ThreadLocal<UserModel>();
    
    public static void setRequestUser(UserModel userModel)
    {
        map.set(userModel);
    }
    
    public static UserModel getRequestUser()
    {
        return map.get();
    }

	@RequestMapping(value = "/getLambdaUrl", produces = "text/plain;charset=UTF-8")
	public String getLambdaUrl() {
		return AppConstants.getLambdaUrl();
	}
	
}
