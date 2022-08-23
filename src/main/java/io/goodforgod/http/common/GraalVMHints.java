package io.goodforgod.http.common;

import io.goodforgod.graalvm.hint.annotation.InitializationHint;

@InitializationHint(typeNames = {
        "io.goodforgod.http.common",
        "io.goodforgod.http.common.uri",
        "io.goodforgod.http.common.exception" },
        value = InitializationHint.InitPhase.BUILD)
final class GraalVMHints {

    private GraalVMHints() {}
}
