package bhup9.Redis.Repository;

import bhup9.Redis.Entity.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository{


    public static final String KEY = "Employee";  // TO store data one of the hash

//    private RedisTemplate<Integer,Employee> redisTemplate;

    private HashOperations hashOperations;
    private RedisOperations redisTemplate;

   // public EmployeeRepository(RedisTemplate<Integer,Employee> redisTemplate){
    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.hashOperations= redisTemplate.opsForHash();
        this.redisTemplate=redisTemplate;
    }
    public void create(Employee employee){
        System.out.println("DATA SAVED IN DB");
        hashOperations.put(KEY,employee.getId(),employee);
       // hashOperations.put("Employee",employee.getId(),employee);
    }

    public List<Employee> getAll(){
        return hashOperations.values("Employee");
   }
   // public Map<Integer,Employee> getAll(){
     //   return hashOperations.entries(KEY);
    //}

    public Employee getById(int id){
        System.out.println("GET BY ID FROM DB");
        return (Employee) hashOperations.get(KEY,id);
        // return (Employee)hashOperations.get("Employee",id);
    }

    public String update(int id,Employee employee){
        System.out.println("DATA UPDATED");
        hashOperations.put(KEY,id,employee);

        //hashOperations.put("Employee",id,employee);
        return "Data Successfully Updated";
    }

    public void delete(int id){
        System.out.println("DATA DELETED FROM DB");
        hashOperations.delete(KEY,id);
       // hashOperations.delete("Employee",id);
    }

}
