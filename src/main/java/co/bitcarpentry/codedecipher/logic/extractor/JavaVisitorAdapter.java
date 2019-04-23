package co.bitcarpentry.codedecipher.logic.extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import co.bitcarpentry.codedecipher.model.Attribute;
import co.bitcarpentry.codedecipher.model.Class;
import co.bitcarpentry.codedecipher.model.DataType;
import co.bitcarpentry.codedecipher.model.Method;
import co.bitcarpentry.codedecipher.model.VisibilityKind;;

public class JavaVisitorAdapter extends VoidVisitorAdapter<Object>{

	protected List<Class> classes;
	protected Class currentClass;
	protected Logger logger;
	
	public JavaVisitorAdapter() {
		super();
		this.classes = new ArrayList<>();
		this.logger  = Logger.getLogger(this.getClass().getSimpleName());
	}		
	
	public List<Class> getClasses() {
		return classes;
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {
		this.logger.info( n.getName().asString() );
		this.currentClass = new Class(n.getNameAsString());		
		this.classes.add(this.currentClass);
		super.visit(n, arg);
	}

	private VisibilityKind getVisibilityKind(NodeList<Modifier> modifiers) {
		VisibilityKind visibilityKind = null;
		for( Modifier modifier : modifiers ) {
			switch( modifier.getKeyword() ) {
				case PRIVATE:{
					visibilityKind = VisibilityKind.PRIVATE;
				}break;
				case DEFAULT:{
					visibilityKind = VisibilityKind.PACKAGE;
				}break;
				case PROTECTED:{
					visibilityKind = VisibilityKind.PROTECTED;
				}break;
				case PUBLIC:{
					visibilityKind = VisibilityKind.PUBLIC;
				}break;
				default:{
					visibilityKind = VisibilityKind.PACKAGE;
				}break;
			}
		}
		return visibilityKind;
	}
	
	@Override
	public void visit(FieldDeclaration n, Object arg) {
		String name = null;
		VisibilityKind visibilityKind = this.getVisibilityKind(n.getModifiers());		
		DataType type = null;
		VariableDeclarator variableDeclarator = n.getVariable(0);
		name = variableDeclarator.getName().asString();
		type = new DataType(variableDeclarator.getType().asString(), variableDeclarator.getType().asString(), visibilityKind);
		System.out.println(variableDeclarator.getType());		
		//Attribute attribute = new Attribute(owningClass, name, visibilityKind, type, parameters)
		this.currentClass.getAttributes().add( new Attribute(this.currentClass, name, visibilityKind, type) );
		super.visit(n, arg);
	}

	@Override
	public void visit(MethodDeclaration n, Object arg) {
		String name = n.getNameAsString();
		VisibilityKind visibilityKind = this.getVisibilityKind(n.getModifiers());
		DataType type = new DataType(n.getType().asString(), n.getType().asString(), visibilityKind);	
		List<DataType> parameters = null;		
		if( n.getParameters() != null && n.getParameters().size() > 0 ) {
			parameters = new ArrayList<>();
			for( Parameter parameter : n.getParameters() ) {
				parameters.add( new DataType( parameter.getNameAsString(), parameter.getNameAsString(), this.getVisibilityKind(parameter.getModifiers()) ) );
			}
		}
		Method method = new Method(this.currentClass, name, visibilityKind, type, parameters);		
		this.currentClass.getMethods().add(method);		
		super.visit(n, arg);
	}
	
	
}
