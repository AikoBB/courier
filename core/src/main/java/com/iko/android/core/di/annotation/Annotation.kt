package com.iko.android.modularapp.di.annotation
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScopeActivity


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScopeService


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScopeFragment

