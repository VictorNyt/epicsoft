<script setup lang="ts">
definePageMeta({ layout: false });
const principal = usePrincipal();
const token = useCookie("token");
const { $auth, $error } = useNuxtApp()
const dados = reactive({ username: "", password: "" })

async function login() {
    try {
        const loginData = await $auth.login(dados);
        principal.value = loginData.user;
        token.value = loginData.token;
        navigateTo("/");
    }
    catch (error) {
        console.log("erro: ", error)
    }
}
</script>
<style>
.login {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

.login form {
    display: flex;
    flex-direction: column;
    gap: 10px;
    min-width: 350px;
}
</style>
<template>
    <div class="login">
        <form @submit.prevent="login">
            <div class="field">
                <label for="username">Usuário:</label>
                <input v-model="dados.username" type="text" name="username" id="username">
            </div>
            <div class="field">
                <label for="password">Senha:</label>
                <input v-model="dados.password" type="password" name="password" id="password">
            </div>
            <button type="submit">Entrar</button>
        </form>
    </div>
</template>