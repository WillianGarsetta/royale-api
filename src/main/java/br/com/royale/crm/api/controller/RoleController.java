package br.com.royale.crm.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.royale.crm.api.dto.AccountDTO;
import br.com.royale.crm.api.dto.FormLogin;
import br.com.royale.crm.api.entity.AccountEntity;
import br.com.royale.crm.api.service.AccountService;
import br.com.royale.crm.api.service.RoleService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "/role")
public class RoleController {
	
	@Autowired
	private RoleService service;
	
	@Operation(summary = "List Roles", description = "Método responsável por listar usuários", tags = "API-role")
	@RequestMapping(path = "/list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno da lista accounts"),
			@ApiResponse(code = 403, message = "Não Permitido executar esta ação"),
			@ApiResponse(code = 500, message = "Erro interno do servidor, necessario atuação tecnica") })
	private ResponseEntity<?> getAccount() {
			
		return service.listAccounts();
	}
	
	
	
}
