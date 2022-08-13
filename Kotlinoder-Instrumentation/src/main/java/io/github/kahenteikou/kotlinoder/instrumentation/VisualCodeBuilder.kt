package io.github.kahenteikou.kotlinoder.instrumentation
//complete
interface VisualCodeBuilder {
    fun declareCompilationUnit(name:String,packageName:String):CompilationUnitDeclaration
    fun assignConstant(scope:Scope?,varName:String,constant:Any?)
    fun assignVariable(scope:Scope?,varNameDest:String,varNameSrc:String)
    fun createInstance(scope:Scope?,type:IType,varName:String,vararg args:Variable?)
    fun createVariable(scope:Scope?,type:IType,varName:String):Variable
    fun declareFor(scope:Scope?,varName:String,from:Int,to:Int,inc:Int):ForDeclaration
    fun declareClass(scope:CompilationUnitDeclaration,type:IType,modifiers:IModifiers,extends:IExtends,implements:IExtends):ClassDeclaration
    fun declareMethod(scope:ClassDeclaration,modifiers:IModifiers,returnType:Type,methodName:String,params:IParameters ):MethodDeclaration
    fun declareWhile(scope:Scope?,check:Invocation):WhileDeclaration
    fun invokeMethod(scope:Scope?,varName:String,mName:String,isVoid:Boolean,retValName:String,vararg args:Variable):Invocation
    fun invokeMethod(scope:Scope?,varName:String,mName:String,isVoid:Boolean,retValName:String,args:List<Variable>):Invocation
    fun invokeStaticMethod(scope:Scope?,type:IType,mName:String,isVoid:Boolean,retValName:String,vararg args:Variable):Invocation
    fun invokeStaticMethod(scope:Scope?,mName:String,isVoid:Boolean,retValName:String,vararg args:Variable):Invocation
    fun invokeStaticMethod(scope:Scope?,type:IType,mName:String,isVoid:Boolean,retValName:String,args:List<Variable>):Invocation
    fun invokeStaticMethod(scope:Scope?,mName:String,isVoid:Boolean,retValName:String, args:List<Variable>):Invocation

}