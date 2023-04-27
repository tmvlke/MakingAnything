package wskim.ksputils

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import wskim.ksputils.IntentBuilderProcessor

class IntentBuilderProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return IntentBuilderProcessor().apply {
            init(environment.codeGenerator, environment.logger)
        }
    }
}