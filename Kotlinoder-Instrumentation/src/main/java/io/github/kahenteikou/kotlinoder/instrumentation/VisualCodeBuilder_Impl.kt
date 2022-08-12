package io.github.kahenteikou.kotlinoder.instrumentation
//complete
import eu.mihosoft.vrl.workflow.FlowFactory
import java.util.*
import eu.mihosoft.vrl.workflow.IdGenerator

class VisualCodeBuilder_Impl : VisualCodeBuilder {
    private final val variables: Stack<String> = Stack()
    private var idRequest:IdRequest= object : IdRequest {
        val generator: IdGenerator = FlowFactory.newIdGenerator()
        override fun request(): String {
            return generator.newId()
        }
    }
    private fun popVariable():String{
        return variables.pop()
    }
    private fun createScope(parent:Scope?,type:ScopeType,name:String,vararg args:Any?):Scope{
        if(parent!=null){
            return parent.createScope(idRequest.request(),type,name,*args)
        }else{
            return ScopeImpl(idRequest.request(),null,type,name,*args)
        }
    }

    override fun declareCompilationUnit(name: String, packageName: String): CompilationUnitDeclaration {
        return CompilationUnitDeclaration_Impl(idRequest.request(),null,name,packageName)
    }
    override fun createVariable(scope:Scope?,type:IType,varName:String):Variable{
        var resultkun: Variable? =scope!!.createVariable(type,varName)
        variables.push(varName)
        return resultkun!!
    }
    fun createVariable(scope:Scope?,type:IType):Variable{
        var resultkun=scope!!.createVariable(type)
        variables.push(resultkun!!.getName())
        return resultkun!!
    }
    override fun declareMethod(scope:ClassDeclaration,modifiers:IModifiers,returnType:Type,methodName:String,params:IParameters ):MethodDeclaration{
        return scope.declareMethod(idRequest.request(),modifiers,returnType,methodName,params)
    }
    override fun declareFor(scope:Scope?,varName:String,from:Int,to:Int,inc:Int):ForDeclaration{
        if(scope!!.getType()== ScopeType.CLASS||scope!!.getType()==ScopeType.INTERFACE) {
            throw UnsupportedOperationException(
                "Unsupported parent scope specified."
                        + " Class ${ScopeType.CLASS} or ${ScopeType.INTERFACE} " +
                        " based implementations are not supported!"
            )
        }
        var resultkun:ForDeclaration=ForDeclaration_Impl(idRequest.request(),
        scope,varName,from,to,inc)
        return resultkun
    }
    override fun declareWhile(scope:Scope?,check:Invocation):WhileDeclaration{
        if(scope!!.getType()== ScopeType.CLASS||scope!!.getType()==ScopeType.INTERFACE) {
            throw UnsupportedOperationException(
                "Unsupported parent scope specified."
                        + " Class ${ScopeType.CLASS} or ${ScopeType.INTERFACE} " +
                        " based implementations are not supported!"
            )
        }
        var resultkun:WhileDeclaration_Impl=WhileDeclaration_Impl(idRequest.request(),
                scope,check)
        scope!!.getControlFlow().callScope(resultkun)
        return resultkun
    }
    override fun createInstance(scope:Scope?,type:IType,varName:String,vararg args:Variable?){
        var id:String=idRequest.request()
        scope!!.getControlFlow().createInstance(id,type,varName,*args)
        variables.push(varName)
    }
    override fun invokeMethod(scope:Scope?,varName:String,mName:String,isVoid:Boolean,retValName:String,vararg args:Variable):Invocation{
        var id:String=idRequest.request()
        var resultkun:Invocation=scope!!.getControlFlow().callMethod(id,varName,mName,isVoid,retValName,*args)
        return resultkun
    }
    override fun invokeStaticMethod(scope:Scope?,type:IType,mName:String,isVoid:Boolean,retValName:String,vararg args:Variable):Invocation{
        var id:String=idRequest.request()
        var resultkun:Invocation=scope!!.getControlFlow().callStaticMethod(id,type,mName,isVoid,retValName,*args)
        return resultkun
    }

    override fun invokeStaticMethod(
        scope: Scope?,
        mName: String,
        isVoid: Boolean,
        retValName: String,
        vararg args: Variable
    ): Invocation {
        var id:String=idRequest.request()
        var resultkun:Invocation=scope!!.getControlFlow().callStaticMethod(id,mName,isVoid,retValName,*args)
        return resultkun
    }

    override fun invokeStaticMethod(
        scope: Scope?,
        type: IType,
        mName: String,
        isVoid: Boolean,
        retValName: String,
        args: List<Variable>
    ): Invocation {
        var id:String=idRequest.request()
        var resultkun:Invocation=scope!!.getControlFlow().callStaticMethod(id,type,mName,isVoid,retValName,*args)
        return resultkun
    }

    override fun invokeStaticMethod(
        scope: Scope?,
        mName: String,
        isVoid: Boolean,
        retValName: String,
        args: List<Variable>
    ): Invocation {
        TODO("Not yet implemented")
    }

    override fun assignConstant(scope:Scope?,varName:String,constant:Any?){
        scope!!.assignConstant(varName,constant)
    }
    override fun assignVariable(scope:Scope?,varNameDest:String,varNameSrc:String){
        scope!!.assignVariable(varNameDest,varNameSrc)
    }
    fun setIdRequest(idRequest:IdRequest){
        this.idRequest=idRequest
    }
    override fun declareClass(scope:CompilationUnitDeclaration,type:IType,modifiers:IModifiers,extends:IExtends,implements:IExtends):ClassDeclaration {
        var id: String = idRequest.request()
        var resultkun: ClassDeclaration =ClassDeclaration_Impl(id,scope,type,modifiers,extends,implements)
        return resultkun
    }
}