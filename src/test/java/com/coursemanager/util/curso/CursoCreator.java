package com.coursemanager.util.curso;

import com.coursemanager.domain.model.CursoEntidade;

public class CursoCreator {
	public static CursoEntidade cursoInput() {
		CursoEntidade curso= new CursoEntidade();
		curso.setCode("code");
		curso.setDescription("descricao");
		curso.setDuration(8.5f);;
		curso.setImageUrl("img url");
		curso.setLink_de_acesso("link de acesso");
		curso.setNome("nome");
		curso.setPrice(44.55f);
		curso.setRating((short) 4);
		return curso;
	}

}
