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

import br.com.royale.crm.api.dto.FormLogin;
import br.com.royale.crm.api.entity.AccountEntity;
import br.com.royale.crm.api.service.AccountService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "/account")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@Operation(summary = "List Accounts", description = "Método responsável por listar usuários", tags = "account-controller")
	@RequestMapping(path = "/list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno da lista accounts"),
			@ApiResponse(code = 403, message = "Não Permitido executar esta ação"),
			@ApiResponse(code = 500, message = "Erro interno do servidor, necessario atuação tecnica") })
	private ResponseEntity<?> getAccount() {
			
		return service.listAccounts();
	}
	
	@Operation(summary = "Create Accounts", description = "Método responsável por Criar Accounts", tags = "account-controller")
	@ResponseBody
	@RequestMapping(path = "/create", method = RequestMethod.POST, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno da Criação de Account"),
			@ApiResponse(code = 403, message = "Não Permitido executar esta ação"),
			@ApiResponse(code = 500, message = "Erro interno do servidor, necessario atuação tecnica") })
	private ResponseEntity postCreateAccount(@RequestBody @Valid AccountEntity account) {
			
		return service.createAccount(account);
	}
	
	@Operation(summary = "Update Accounts", description = "Método responsável por Atualizar Accounts", tags = "account-controller")
	@RequestMapping(path = "/update", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno da Atualização de Account"),
			@ApiResponse(code = 403, message = "Não Permitido executar esta ação"),
			@ApiResponse(code = 500, message = "Erro interno do servidor, necessario atuação tecnica") })
	private ResponseEntity putUpdateAccount(@RequestBody @Valid AccountEntity account) {
			
		return service.updateAccount(account);
	}
	
	@Operation(summary = "Delete Accounts", description = "Método responsável por Exclusão Fisica Accounts", tags = "account-controller")
	@RequestMapping(path = "/delete/{uuid}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno da deleção de Account"),
			@ApiResponse(code = 403, message = "Não Permitido executar esta ação"),
			@ApiResponse(code = 500, message = "Erro interno do servidor, necessario atuação tecnica") })
	private ResponseEntity<?> deleteAccount(@PathVariable String uuid) {
			
		return service.deleteAccount(uuid);
	}
	
	@Operation(summary = "Exclude Accounts", description = "Método responsável por Exclusão Lógica de  Accounts", tags = "account-controller")
	@RequestMapping(path = "/exclude/{uuid}", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno da deleção de Account"),
			@ApiResponse(code = 403, message = "Não Permitido executar esta ação"),
			@ApiResponse(code = 500, message = "Erro interno do servidor, necessario atuação tecnica") })
	private ResponseEntity<?> excludeAccount(@PathVariable String uuid) {
			
		return service.excludeAccount(uuid);
	}
	
	@Operation(summary = "Activate Accounts", description = "Método responsável por Ativação Lógica de  Accounts", tags = "account-controller")
	@RequestMapping(path = "/activate/{uuid}", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno da deleção de Account"),
			@ApiResponse(code = 403, message = "Não Permitido executar esta ação"),
			@ApiResponse(code = 500, message = "Erro interno do servidor, necessario atuação tecnica") })
	private ResponseEntity<?> activateAccount(@PathVariable String uuid) {
			
		return service.activateAccount(uuid);
	}
	
	@Operation(summary = "Auth Accounts", description = "Método responsável por autenticação de Accounts", tags = "account-controller")
	@RequestMapping(path = "/auth", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno da auth de Account"),
			@ApiResponse(code = 403, message = "Não Permitido executar esta ação"),
			@ApiResponse(code = 500, message = "Erro interno do servidor, necessario atuação tecnica") })
	private ResponseEntity<?> authenticationAccount(@RequestBody FormLogin login) {
			
		return service.authenticationAccount(login);
	}
	
	
	
}
