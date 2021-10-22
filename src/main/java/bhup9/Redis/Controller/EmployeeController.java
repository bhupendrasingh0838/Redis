package bhup9.Redis.Controller;

import bhup9.Redis.Entity.Employee;
import bhup9.Redis.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @PostMapping("/create")
    public String create(@RequestBody Employee employee)
    {
        service.create(employee);
        return "Data Successfully Created";
    }

    @GetMapping("/getAll")
    //public Map<Integer,Employee> getAll(){
     //   return service.getAll();
   // }

   public List<Employee> getAll()
    {
       return service.getAll();
    }
    @GetMapping("/getById/{id}")
   //public Employee getById(@PathVariable int id){
    public Employee getById(@PathVariable int id){
        return service.getById(id);
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable int id, @RequestBody Employee employee){
        service.update(id,employee);
        return "Data SuccessFully Updated";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        service.delete(id);
        return "Data Successfully Deleted";
    }
}
