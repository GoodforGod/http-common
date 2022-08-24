package io.goodforgod.http.common;

import io.goodforgod.graalvm.hint.annotation.InitializationHint;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 21.05.2022
 */
@InitializationHint(value = InitializationHint.InitPhase.BUILD,
        typeNames = "io.goodforgod.http.common")
final class GraalVMHint {

    private GraalVMHint() {}
}
