package com.repaso.repaso;

import com.repaso.repaso.persistence.model.Rol;
import com.repaso.repaso.services.RolServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RepasoApplication implements CommandLineRunner {

	@Autowired
	RolServiceI rolMngmnt;

	public static void main(String[] args) {
		SpringApplication.run(RepasoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Rol r = new Rol();
		r.setRolId(1L);
		r.setRolName("ADMIN");

		Rol r2 = new Rol();
		r2.setRolId(2L);
		r2.setRolName("USER");

		rolMngmnt.saveRol(r);
		rolMngmnt.saveRol(r2);
	}
}
