package com.repaso.repaso.controller;

import com.repaso.repaso.persistence.model.Rol;
import com.repaso.repaso.services.RolServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RolController {

    @Autowired
    RolServiceI rolMngmnt;

    @PostMapping("/insert")
    void insertRol(@RequestBody Rol rol) {
        rolMngmnt.saveRol(rol);
    }
}
