package JDBC.control;
import JDBC.Dao.All;
import JDBC.Dao.employee;
import JDBC.Dao.today;
import JDBC.Impl.employeesImpl;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class mainControl {
    private static final Logger logger = LoggerFactory.getLogger(mainControl.class);
    @Autowired
    employeesImpl employeeImpl;
    String table;
    @GetMapping("/main")
    public String main() {
        return "main";
    }
    @GetMapping("/setTables")
    public void setTables(@RequestParam(name="table", required=false, defaultValue="World")  String table) {
        employeeImpl.setTable(table);
        this.table=table;
    }
    @GetMapping("/showTables")
    public String showTables(){
        return  "showTables";
    }
    @PostMapping("/findColumn")
    public @ResponseBody Object findColumn(){
        return employeeImpl.findCha();
    }

    @PostMapping("/add")
    public @ResponseBody
    Map<String, Object> add( @RequestBody All all){
        Map<String, Object> result = new HashMap<>();
        employeeImpl.add(all);
        return result;
    }
    @PostMapping("/update")
    public @ResponseBody
    Map<String, Object> update( @RequestBody All all){
        Map<String, Object> result = new HashMap<>();
        employeeImpl.update(all);
        // result.put("eid",employee.getEid());
        System.out.println();
        return result;
    }
    @PostMapping("/delete")
    public @ResponseBody
    Map<String, Object> delete( @RequestBody All all){
        Map<String, Object> result = new HashMap<>();
        employeeImpl.delete(all);
        // result.put("eid",employee.getEid());
        return result;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    @PostMapping("/findAll")
    public @ResponseBody Object findAll(){
        if (table.equals("employees"))
            return employeeImpl.findAll();
        else if (table.equals("customers"))
            return employeeImpl.findCustomer();
        else  if (table.equals("logs"))
            return employeeImpl.findLogs();
        else if (table.equals("products")){
            return employeeImpl.findProducts();
        }
        else if (table.equals("purchases"))
            return employeeImpl.findPurchases();
        else
            return employeeImpl.findSuppliers();
    }


    @GetMapping("/summer")
    public String summer(Model model){
        List<today> list=employeeImpl.daySum();
        model.addAttribute("today",list.get(0).getToday());
        model.addAttribute("remain",list.get(0).getRemain());
        return "summer";
    }
}
