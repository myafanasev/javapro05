package ru.innotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innotech.dto.User;
import ru.innotech.dto.UserProduct;
import ru.innotech.exception.ProductNotFound;
import ru.innotech.exception.UserNotFound;
import ru.innotech.service.UserProductService;
import ru.innotech.service.UserService;

import java.util.List;

@RestController
public class RestUserController {
    UserService userService;
    UserProductService userProductService;
    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }
    @Autowired
    public void setUserProductService(UserProductService userProductService) { this.userProductService = userProductService; }

    @GetMapping("/{id}/getAllProducts")
    public List<UserProduct> getAllProducts(@PathVariable("id") int idUser) {
        User user = userService.findId(idUser);
        if (user==null) throw new UserNotFound(); // если пользователь не найден, бросаем исключение
        return userProductService.findAllProductClient(user.getId());
    }

    // возвращает продукт с id, указанным в параметрах запроса
    @GetMapping("/getProduct")
    @ResponseStatus(HttpStatus.OK)
    public UserProduct getProduct(@RequestParam("id") int idProduct) {
        UserProduct userProduct = userProductService.findId(idProduct);
        if (userProduct==null) throw new ProductNotFound(); // если продукт не найден, бросаем исключение
        return userProduct;
    }

//    public ResponseEntity<UserProduct> getProduct(@RequestParam("id") int idProduct) {
//        UserProduct userProduct = userProductService.findId(idProduct);
//        //return ResponseEntity.ok().body(userProduct);
//
//        return ResponseEntity.status(404).build();
//    }
}
