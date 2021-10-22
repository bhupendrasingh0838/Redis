package bhup9.Redis.Service;

import bhup9.Redis.Entity.Employee;
import bhup9.Redis.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@EnableCaching
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;

    public void create(Employee employee){

        repository.create(employee);
    }

     public List<Employee> getAll(){
       return (List<Employee>) repository.getAll();
    }
   // public Map<Integer,Employee> getAll(){

    //    return repository.getAll();
   // }

    @Cacheable(key = "#id", value = "Employee")
    public Employee getById(int id){
        return repository.getById(id);
    }

    @CachePut(key = "#id",value ="Employee")
    public Employee update(int id,Employee employee){
        Employee employee1 = repository.getById(id);
        employee1.setId(employee.getId());
        employee1.setName(employee.getName());
        employee1.setAge(employee.getAge());
        repository.update(id, employee1);
        return employee1;
    }

    @CacheEvict(key = "#id", value = "Employee")
    public void delete(int id){

        repository.delete(id);
    }

}
