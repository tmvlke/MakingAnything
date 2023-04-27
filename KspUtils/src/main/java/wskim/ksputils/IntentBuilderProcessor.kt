package wskim.ksputils

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import wskim.ksputils.annotation.IntentBuilder

class IntentBuilderProcessor : SymbolProcessor {

    companion object {
        private val intentBuilderName = IntentBuilder::class.java.canonicalName
    }

    // 코드 생성기
    private lateinit var codeGenerator: CodeGenerator
    // 로그 출력기
    private lateinit var logger: KSPLogger

    fun init(
        codeGenerator: CodeGenerator,
        logger: KSPLogger
    ) {
        this.codeGenerator = codeGenerator
        this.logger = logger
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.warn("IntentBuilderProcessor 시작")

        // getSymbolsWithAnnotation을 통해 원하는 심볼을 가져와 작업을 수행할 수 있다.
        val symbols:Sequence<KSAnnotated> = resolver.getSymbolsWithAnnotation(intentBuilderName)

        val ret = symbols.filter { !it.validate() }

        // IntentBuilderVisitor에게 작업을 위임한다.
        symbols
            .filter { it is KSClassDeclaration && it.validate() }
            .forEach { it.accept(IntentBuilderVisitor(codeGenerator, logger), Unit) }
        return ret.toList()
    }

    override fun finish() {
        logger.warn("IntentBuilderProcessor 종료")
    }

    override fun onError() {
        logger.error("IntentBuilderProcessor 에러")
    }

}