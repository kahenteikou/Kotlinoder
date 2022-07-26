package io.github.kahenteikou.kotlinoder.instrumentation

import ktast.ast.Node

open class CustomVisitor {
    fun visit(v:Node)=visit(v,null)
    protected open fun visit(v: Node, parent: Node?)=v.run{
        when (this) {
            is Node.CommaSeparatedNodeList<*> -> {
                visitChildren(elements)
                visitChildren(trailingComma)
            }
            is Node.NodeList<*> -> {
                visitChildren(elements)
            }
            is Node.HasSimpleStringRepresentation -> {}
            is Node.KotlinFile -> {
                visitChildren(annotationSets)
                visitChildren(packageDirective)
                visitChildren(importDirectives)
                visitChildren(declarations)
            }
            is Node.KotlinScript -> {
                visitChildren(annotationSets)
                visitChildren(packageDirective)
                visitChildren(importDirectives)
                visitChildren(expressions)
            }
            is Node.PackageDirective -> {
                visitPackageDirective(this,v)
            }
            is Node.ImportDirective -> {
                visitChildren(importKeyword)
                visitChildren(names)
                visitChildren(alias)
            }
            is Node.ImportDirective.Alias -> {
                visitChildren(name)
            }
            is Node.Declaration.Class -> {
                visitClass(this,v)
            }
            is Node.Declaration.Class.Parent.CallConstructor -> {
                visitChildren(type)
                visitChildren(typeArgs)
                visitChildren(args)
                visitChildren(lambda)
            }
            is Node.Declaration.Class.Parent.DelegatedType -> {
                visitChildren(type)
                visitChildren(byKeyword)
                visitChildren(expression)
            }
            is Node.Declaration.Class.Parent.Type -> {
                visitChildren(type)
            }
            is Node.Declaration.Class.PrimaryConstructor -> {
                visitChildren(modifiers)
                visitChildren(constructorKeyword)
                visitChildren(params)
            }
            is Node.Declaration.Class.Body -> {
                visitChildren(enumEntries)
                visitChildren(declarations)
            }
            is Node.Declaration.Init -> {
                visitChildren(modifiers)
                visitChildren(block)
            }
            is Node.Declaration.Function -> {
                visitFunctionDeclaration(this,v)
            }
            is Node.Declaration.Function.Param -> {
                visitChildren(modifiers)
                visitChildren(valOrVar)
                visitChildren(name)
                visitChildren(typeRef)
                visitChildren(equals)
                visitChildren(defaultValue)
            }
            is Node.Declaration.Property -> {
                visitChildren(modifiers)
                visitChildren(valOrVar)
                visitChildren(typeParams)
                visitChildren(receiverTypeRef)
                visitChildren(lPar)
                visitChildren(variables)
                visitChildren(trailingComma)
                visitChildren(rPar)
                visitChildren(typeConstraints)
                visitChildren(equals)
                visitChildren(initializer)
                visitChildren(delegate)
                visitChildren(accessors)
            }
            is Node.Declaration.Property.Delegate -> {
                visitChildren(byKeyword)
                visitChildren(expression)
            }
            is Node.Declaration.Property.Variable -> {
                visitChildren(name)
                visitChildren(typeRef)
            }
            is Node.Declaration.Property.Accessor.Getter -> {
                visitChildren(modifiers)
                visitChildren(getKeyword)
                visitChildren(typeRef)
                visitChildren(postModifiers)
                visitChildren(equals)
                visitChildren(body)
            }
            is Node.Declaration.Property.Accessor.Setter -> {
                visitChildren(modifiers)
                visitChildren(setKeyword)
                visitChildren(params)
                visitChildren(postModifiers)
                visitChildren(equals)
                visitChildren(body)
            }
            is Node.Declaration.TypeAlias -> {
                visitChildren(modifiers)
                visitChildren(name)
                visitChildren(typeParams)
                visitChildren(typeRef)
            }
            is Node.Declaration.SecondaryConstructor -> {
                visitChildren(modifiers)
                visitChildren(constructorKeyword)
                visitChildren(params)
                visitChildren(delegationCall)
                visitChildren(block)
            }
            is Node.Declaration.SecondaryConstructor.DelegationCall -> {
                visitChildren(target)
                visitChildren(args)
            }
            is Node.EnumEntry -> {
                visitChildren(modifiers)
                visitChildren(name)
                visitChildren(args)
                visitChildren(body)
            }
            is Node.TypeParam -> {
                visitChildren(modifiers)
                visitChildren(name)
                visitChildren(typeRef)
            }
            is Node.TypeArg -> {
                visitChildren(modifiers)
                visitChildren(typeRef)
            }
            is Node.TypeRef -> {
                visitChildren(lPar)
                visitChildren(modifiers)
                visitChildren(innerLPar)
                visitChildren(innerMods)
                visitChildren(type)
                visitChildren(innerRPar)
                visitChildren(rPar)
            }
            is Node.Type.Function -> {
                visitChildren(contextReceivers)
                visitChildren(receiver)
                visitChildren(params)
                visitChildren(typeRef)
            }
            is Node.Type.Function.ContextReceiver -> {
                visitChildren(typeRef)
            }
            is Node.Type.Function.Receiver -> {
                visitChildren(typeRef)
            }
            is Node.Type.Function.Param -> {
                visitChildren(name)
                visitChildren(typeRef)
            }
            is Node.Type.Simple -> {
                visitChildren(pieces)
            }
            is Node.Type.Simple.Piece -> {
                visitChildren(name)
                visitChildren(typeArgs)
            }
            is Node.Type.Nullable -> {
                visitChildren(lPar)
                visitChildren(modifiers)
                visitChildren(type)
                visitChildren(rPar)
            }
            is Node.Type.Dynamic -> {}
            is Node.ValueArg -> {
                visitChildren(name)
                visitChildren(expression)
            }
            is Node.ExpressionContainer -> {
                visitChildren(expression)
            }
            is Node.Expression.If -> {
                visitChildren(ifKeyword)
                visitChildren(condition)
                visitChildren(body)
                visitChildren(elseBody)
            }
            is Node.Expression.Try -> {
                visitChildren(block)
                visitChildren(catches)
                visitChildren(finallyBlock)
            }
            is Node.Expression.Try.Catch -> {
                visitChildren(catchKeyword)
                visitChildren(params)
                visitChildren(block)
            }
            is Node.Expression.For -> {
                visitChildren(forKeyword)
                visitChildren(loopParam)
                visitChildren(loopRange)
                visitChildren(body)
            }
            is Node.Expression.While -> {
                visitChildren(whileKeyword)
                visitChildren(condition)
                visitChildren(body)
            }
            is Node.Expression.Binary -> {
                visitExpressionBinary(this,v)
            }
            is Node.Expression.BinaryInfix -> {
                visitChildren(lhs)
                visitChildren(operator)
                visitChildren(rhs)
            }
            is Node.Expression.Unary -> {
                visitChildren(expression)
                visitChildren(operator)
            }
            is Node.Expression.BinaryType -> {
                visitChildren(lhs)
                visitChildren(operator)
                visitChildren(rhs)
            }
            is Node.Expression.CallableReference -> {
                visitChildren(lhs)
                visitChildren(rhs)
            }
            is Node.Expression.ClassLiteral -> {
                visitChildren(lhs)
            }
            is Node.Expression.DoubleColon.Receiver.Expression -> {
                visitChildren(expression)
            }
            is Node.Expression.DoubleColon.Receiver.Type -> {
                visitChildren(type)
                visitChildren(questionMarks)
            }
            is Node.Expression.Parenthesized -> {
                visitChildren(expression)
            }
            is Node.Expression.StringTemplate -> {
                visitChildren(entries)
            }
            is Node.Expression.StringTemplate.Entry.Regular -> {}
            is Node.Expression.StringTemplate.Entry.ShortTemplate -> {}
            is Node.Expression.StringTemplate.Entry.UnicodeEscape -> {}
            is Node.Expression.StringTemplate.Entry.RegularEscape -> {}
            is Node.Expression.StringTemplate.Entry.LongTemplate -> {
                visitChildren(expression)
            }
            is Node.Expression.Constant -> {}
            is Node.Expression.Lambda -> {
                visitChildren(params)
                visitChildren(body)
            }
            is Node.Expression.Lambda.Param -> {
                visitChildren(lPar)
                visitChildren(variables)
                visitChildren(trailingComma)
                visitChildren(rPar)
                visitChildren(colon)
                visitChildren(destructTypeRef)
            }
            is Node.Expression.Lambda.Param.Variable -> {
                visitChildren(modifiers)
                visitChildren(name)
                visitChildren(typeRef)
            }
            is Node.Expression.Lambda.Body -> {
                visitChildren(statements)
            }
            is Node.Expression.This -> {}
            is Node.Expression.Super -> {
                visitChildren(typeArg)
            }
            is Node.Expression.When -> {
                visitChildren(lPar)
                visitChildren(expression)
                visitChildren(rPar)
                visitChildren(branches)
            }
            is Node.Expression.When.Branch.Conditional -> {
                visitChildren(conditions)
                visitChildren(trailingComma)
                visitChildren(body)
            }
            is Node.Expression.When.Branch.Else -> {
                visitChildren(elseKeyword)
                visitChildren(body)
            }
            is Node.Expression.When.Condition.Expression -> {
                visitChildren(expression)
            }
            is Node.Expression.When.Condition.In -> {
                visitChildren(expression)
            }
            is Node.Expression.When.Condition.Is -> {
                visitChildren(typeRef)
            }
            is Node.Expression.Object -> {
                visitChildren(declaration)
            }
            is Node.Expression.Throw -> {
                visitChildren(expression)
            }
            is Node.Expression.Return -> {
                visitChildren(expression)
            }
            is Node.Expression.Continue -> {}
            is Node.Expression.Break -> {}
            is Node.Expression.CollectionLiteral -> {
                visitChildren(expressions)
                visitChildren(trailingComma)
            }
            is Node.Expression.Name -> {}
            is Node.Expression.Labeled -> {
                visitChildren(expression)
            }
            is Node.Expression.Annotated -> {
                visitChildren(annotationSets)
                visitChildren(expression)
            }
            is Node.Expression.Call -> {
                visitExpressionCall(this,v)
            }
            is Node.Expression.Call.LambdaArg -> {
                visitChildren(annotationSets)
                visitChildren(expression)
            }
            is Node.Expression.ArrayAccess -> {
                visitChildren(expression)
                visitChildren(indices)
                visitChildren(trailingComma)
            }
            is Node.Expression.AnonymousFunction -> {
                visitChildren(function)
            }
            is Node.Expression.Property -> {
                visitChildren(declaration)
            }
            is Node.Expression.Block -> {
                visitChildren(statements)
            }
            is Node.Modifier.AnnotationSet -> {
                visitChildren(atSymbol)
                visitChildren(target)
                visitChildren(colon)
                visitChildren(lBracket)
                visitChildren(annotations)
                visitChildren(rBracket)
            }
            is Node.Modifier.AnnotationSet.Annotation -> {
                visitChildren(type)
                visitChildren(args)
            }
            is Node.PostModifier.TypeConstraints -> {
                visitChildren(whereKeyword)
                visitChildren(constraints)
            }
            is Node.PostModifier.TypeConstraints.TypeConstraint -> {
                visitChildren(annotationSets)
                visitChildren(name)
                visitChildren(typeRef)
            }
            is Node.PostModifier.Contract -> {
                visitChildren(contractKeyword)
                visitChildren(contractEffects)
            }
            is Node.PostModifier.Contract.ContractEffect -> {
                visitChildren(expression)
            }
            is Node.Extra -> {}
            else -> error("Expected to be unreachable here. Missing visitor implementation for $this.")
        }
    }
    //module ls
    protected open fun visitPackageDirective(p:Node.PackageDirective,v:Node){
        v.visitChildren(p.modifiers)
        v.visitChildren(p.packageKeyword)
        v.visitChildren(p.names)
    }
    protected open fun visitClass(clsNode:Node.Declaration.Class, v:Node){

        v.visitChildren(clsNode.modifiers)
        v.visitChildren(clsNode.declarationKeyword)
        v.visitChildren(clsNode.name)
        v.visitChildren(clsNode.typeParams)
        v.visitChildren(clsNode.primaryConstructor)
        v.visitChildren(clsNode.parents)
        v.visitChildren(clsNode.typeConstraints)
        v.visitChildren(clsNode.body)
    }
    protected open fun visitFunctionDeclaration(f:Node.Declaration.Function, v:Node){
        v.visitChildren(f.modifiers)
        v.visitChildren(f.funKeyword)
        v.visitChildren(f.typeParams)
        v.visitChildren(f.receiverTypeRef)
        v.visitChildren(f.name)
        v.visitChildren(f.params)
        v.visitChildren(f.typeRef)
        v.visitChildren(f.postModifiers)
        v.visitChildren(f.equals)
        v.visitChildren(f.body)
    }
    protected open fun visitExpressionCall(c:Node.Expression.Call,v:Node){

        v.visitChildren(c.expression)
        v.visitChildren(c.typeArgs)
        v.visitChildren(c.args)
        v.visitChildren(c.lambdaArg)
    }
    protected open fun visitExpressionBinary(b:Node.Expression.Binary,v:Node){
        v.visitChildren(b.lhs)
        v.visitChildren(b.operator)
        v.visitChildren(b.rhs)
    }
    protected fun <T : Node> Node.visitChildren(v: T?) {
        if (v != null) {
            visit(v, this)
        }
    }

    protected fun <T : Node> Node.visitChildren(v: List<T>) {
        v.forEach { orig -> visit(orig, this) }
    }
    companion object{
        fun visit(v:Node,fn: (v: Node, parent: Node?) -> Unit)=object:CustomVisitor(){
            override fun visit(v: Node, parent: Node?) {
                fn(v,parent)
                super.visit(v,parent)
            }
        }
    }
}