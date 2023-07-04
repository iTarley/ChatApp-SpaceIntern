package com.space.chatapp.utils.mapper

interface ModelMapper<in MODEL_A, out MODEL_B> {
    operator fun invoke(model: MODEL_A): MODEL_B
}