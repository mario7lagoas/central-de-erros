	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		String mensagemUsuario = messageSource.getMessage("messagem.invalida", null, 
				LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros , headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Erro> criarListaDeErros(BindingResult bindingResult){
		
		List<Erro> erros = new ArrayList<>();
		
		for ( FieldError fieldError : bindingResult.getFieldErrors() ) { 
			
			
			String mensagemUsusario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolverdor = fieldError.toString();
					
			erros.add(new Erro(mensagemUsusario, mensagemDesenvolverdor));
		}
		
		return erros;
		
	}
	
	
	public static class Erro {
		
		private String mensagemUsuario;
		private String mensagemDesenvolvendor;
		
		public Erro(String mensagemUsuario, String mensagemDesenvolvendor) {
			//super();
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvendor = mensagemDesenvolvendor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvendor() {
			return mensagemDesenvolvendor;
		}
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	//@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> EmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
		
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros , new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		
	}
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> HandleDataIntegrityViolationException(DataIntegrityViolationException ex,WebRequest request){
		String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros , new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
				
	}
