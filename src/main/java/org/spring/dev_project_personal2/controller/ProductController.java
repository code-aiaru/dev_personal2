package org.spring.dev_project_personal2.controller;

import lombok.RequiredArgsConstructor;
import org.spring.dev_project_personal2.dto.ProductDTO;
import org.spring.dev_project_personal2.service.ProductService;
import org.spring.dev_project_personal2.service.ProductUtilService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor // Service 클래스를 호출하기 위함
@RequestMapping("/product")
public class ProductController {

    // 생성자 주입방식으로 의존성을 주입 받는다.
    private final ProductService productService;
    private final ProductUtilService productUtilService;

    // WRITE (INSERT)
    // 게시물 작성 페이지
    @GetMapping("/write")
    public String getProductWrite() {
        return "/product/write";
    }

    // WRITE PROCESS (INSERT)
    // 게시물 작성 처리 시
    @PostMapping("/write")
    public String postProductWrite(@ModelAttribute ProductDTO productDTO) {
        productService.productWriteDetail(productDTO);
        return "index";
    }

    // LIST - with pagination & search (READ)
    // /product/list?page=1
    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(name = "page", defaultValue = "1") int page,
                       @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(name = "searchType", required = false) String searchType,
                       @RequestParam(name = "searchKeyword", required = false) String searchKeyword) {

        Pageable adjustedPageable = PageRequest.of(page - 1, pageable.getPageSize(), pageable.getSort());
        Page<ProductDTO> productList;

        if (searchKeyword == null || searchType == null) {
            productList = productService.productNoSearchList(adjustedPageable);
        } else {
            productList = productService.productSearchList(searchType, searchKeyword, adjustedPageable);
        }

        int nowPage = productList.getPageable().getPageNumber() + 1;
        int totalPage = productList.getTotalPages();
        int startPage = ProductService.calculateStartPage(nowPage, totalPage);
        int endPage = ProductService.calculateEndPage(nowPage, totalPage);

        model.addAttribute("productList", productList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchKeyword", searchKeyword);

        return "/product/list";
    }

    // DETAIL (SELECT)
    @GetMapping("/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        // updateHits 메소드를 호출, 해당 게시글의 조회수를 하나 올린다.
        productUtilService.updateHits(id);
        // detail 메소드를 호출, dto 객체로 가져온다.
        ProductDTO productDTOdetail = productService.productViewDetail(id);
        // product라는 파라미터에 담아서,
        model.addAttribute("product", productDTOdetail);
        // detail.html에 출력
        return "/product/detail";
    }

    // UPDATE (UPDATE)
    @GetMapping("/update/{id}")
    public String getProductUpdate(@PathVariable Long id, Model model) {
        ProductDTO productDTOupdate = productService.productViewDetail(id);
        model.addAttribute("productUpdate", productDTOupdate);
        return "/product/update";
    }

    // UPDATE PROCESS (UPDATE)
    @PostMapping("/update")
    public String postProductUpdate(@ModelAttribute ProductDTO productDTO, Model model) {
        ProductDTO productDTOupdatepro = productService.productUpdateDetail(productDTO);
        model.addAttribute("product", productDTOupdatepro);
        return "/product/detail";
    }

    // DELETE (DELETE)
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/product/list";
    }



}
