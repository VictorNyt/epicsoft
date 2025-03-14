<script setup lang="ts">
definePageMeta({ middleware: 'auth' })

const principal = usePrincipal();
const dados = reactive({ old_password: "", new_password: "" })
const { $auth, $error } = useNuxtApp();
const message = ref();


async function changepass() {
    message.value = "";
    try {
        await $auth.changepass(dados);
        message.value = "Senha alterada com sucesso"
        dados.new_password = "";
        dados.old_password = "";
    }
    catch {
        message.value = "Senha Incorreta"
    }
}

</script>

<template>
    <div class="change">
        <form @submit.prevent="changepass">
            <div>
                <label for="username">Usuario: {{ principal?.name }}</label>
            </div>
            <div class="field">
                <label for="old_password">Senha Antiga: </label>
                <input v-model="dados.old_password" type="password" name="old_password" id="old_password">
            </div>
            <div class="field">
                <label for="password">Senha:</label>
                <input v-model="dados.new_password" type="password" name="new_password" id="new_password">
            </div>
            <div v-if="message">{{ message }}</div>
            <button type="submit">Alterar Senha</button>
        </form>
    </div>
</template>

<style scoped>
.change {
    padding-left: 155px;
    height: 100vh;
    display: flex;
}

div form {
    display: flex;
    flex-direction: column;
    gap: 10px;
    min-width: 350px;
}

/*style scoped muda apenas essa pagina*/
</style>