package top.fyl.springboot.quickstart.service;

import org.springframework.stereotype.Service;
import top.fyl.springboot.quickstart.enums.RequestType;

/**
 * @author dfysa
 * @data 2024/9/2 下午4:15
 * @description
 */
@Service
public class CustomerService {
    public  String handleRequest(RequestType requestType) {
      return  switch (requestType){
            case QUERY ->handleQuery();
            case COMPLAINT->handleComplaint();
            case SUGGESTION->handleSuggestion();

        };

    }
    public String handleQuery(){
        return "查询请求";
    }

    public String handleComplaint(){
        return "投诉请求";
    }

    public String handleSuggestion(){
        return "建议请求";
    }
}
