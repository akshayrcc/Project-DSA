package com.akshayram.caching;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

  @PostMapping("/employee")
  public String setEmployee(@RequestBody Employee e) {
    System.out.println("Cache Size: "+CacheConstants.EMP_CACHE.size());
    CacheConstants.EMP_CACHE.put(e.getName(), e);
    System.out.println("Cache Size: "+CacheConstants.EMP_CACHE.size());
    return "Successfully added in the cache !";
  }

  @GetMapping("/employee/{empName}")
  public Employee getEmployee(@PathVariable("empName") String empName) {
    System.out.println("Cache Size: "+CacheConstants.EMP_CACHE.size());
    return (Employee) CacheConstants.EMP_CACHE.get(empName);
  }
}
