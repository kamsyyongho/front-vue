package com.skt.doss.portal.front.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.skt.doss.portal.front.api_gateway.v1.domain.SearchCompanyInfo;
import com.skt.doss.portal.front.api_gateway.v1.domain.SearchCompanyList;
import com.skt.doss.portal.front.common.domain.Hello;
import com.skt.doss.portal.front.common.service.CommonService;

@RestController
public class CommonApiController {
  
  @Autowired
  private CommonService commonService;
  
  @GetMapping("/api/comm/hello")
  public ResponseEntity<Hello> hello(@RequestParam String name) throws Exception {
    return ResponseEntity.ok(commonService.hello(name));
  }
  
  @GetMapping("/api/comm/searchCompanyList")
  public ResponseEntity<SearchCompanyList.Res> searchCompanyList() throws Exception {
    return ResponseEntity.ok(commonService.searchCompanyList());
    
//    List<SearchCompanyList.Company> companyList = new ArrayList<>();
//    companyList.add(SearchCompanyList.Company.builder().companyCd("SK0001").companyName("SK바이오팜").updDate("20210128112650").regDate("20210128112650").build());
//    companyList.add(SearchCompanyList.Company.builder().companyCd("SK0002").companyName("SK하이닉스").updDate("20210128112650").regDate("20210128112650").build());
//    companyList.add(SearchCompanyList.Company.builder().companyCd("SK0003").companyName("SK커뮤니케이션").updDate("20210128112650").regDate("20210128112650").build());
//    companyList.add(SearchCompanyList.Company.builder().companyCd("SK0004").companyName("SK씨앤씨").updDate("20210128112650").regDate("20210128112650").build());
//    companyList.add(SearchCompanyList.Company.builder().companyCd("SK0005").companyName("SK플래닛").updDate("20210128112650").regDate("20210128112650").build());
//    SearchCompanyList.Res res = SearchCompanyList.Res.builder().code("SUCCESS").msg("SUCCESS").companyList(companyList).build();
//    return ResponseEntity.ok(res);
    
  }
  
  @GetMapping("/api/comm/searchCompanyInfo/{companyName}")
  public ResponseEntity<SearchCompanyInfo.Res> searchCompanyInfo(@PathVariable String companyName) throws Exception {
    return ResponseEntity.ok(commonService.searchCompanyInfo(companyName));
    
//  List<SearchCompanyInfo.Company> companyList = new ArrayList<>();
//  companyList.add(SearchCompanyInfo.Company.builder().companyCd("SK0001").companyName("SK바이오팜").updDate("20210128112650").regDate("20210128112650").build());
//  companyList.add(SearchCompanyInfo.Company.builder().companyCd("SK0002").companyName("SK하이닉스").updDate("20210128112650").regDate("20210128112650").build());
//  companyList.add(SearchCompanyInfo.Company.builder().companyCd("SK0003").companyName("SK커뮤니케이션").updDate("20210128112650").regDate("20210128112650").build());
//  companyList.add(SearchCompanyInfo.Company.builder().companyCd("SK0004").companyName("SK씨앤씨").updDate("20210128112650").regDate("20210128112650").build());
//  companyList.add(SearchCompanyInfo.Company.builder().companyCd("SK0005").companyName("SK플래닛").updDate("20210128112650").regDate("20210128112650").build());
//  SearchCompanyInfo.Res res = SearchCompanyInfo.Res.builder().code("SUCCESS").msg("SUCCESS").companyList(companyList).build();
//  return ResponseEntity.ok(res);    
  }
}
