package JDBC.control;
import JDBC.Dao.employee;
import JDBC.Impl.employeesImpl;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class mainControl {
    private static final Logger logger = LoggerFactory.getLogger(mainControl.class);
    @Autowired
    employeesImpl employeeImpl;

    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @PostMapping("/findAll")
    public @ResponseBody Object findAll(){
        return employeeImpl.findAll();
    }
    @GetMapping("/employees")
    public String begin(){
        return "employees";
    }
    @PostMapping("/add")
    public @ResponseBody
    Map<String, Object> add( @RequestBody employee employee){
        Map<String, Object> result = new HashMap<>();
        employeeImpl.add(employee);
        result.put("eid",employee.getEid());
        return result;
    }
    @PostMapping("/update")
    public @ResponseBody
    Map<String, Object> update( @RequestBody employee employee){
        Map<String, Object> result = new HashMap<>();
        employeeImpl.update(employee);
        result.put("eid",employee.getEid());
        return result;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    @GetMapping("/delete")
    public
    Map<String, Object> delete(@RequestParam(name="eid", required=false, defaultValue="") String eid) {
        Map<String, Object> result = new HashMap<>();
        System.out.println(eid);
        employee employee=new employee();
        employee.setEid(eid);
        employeeImpl.delete(employee);
        result.put("id", eid);
        return result;
    }
}
