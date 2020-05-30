package asset.control;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/AssetTag")
public class AssetTagController {

    @RequestMapping(value="/showUser",method= RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public String getAll(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("id"));

        model.addAttribute("user", "wj");
        return "showUser";
    }


}
