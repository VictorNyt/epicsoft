<template>
    <nav>
        <div class="hamburguer" @click="show_menu"><span class="material-symbols-outlined">menu</span></div>
        <ul class="menu" :class="{ show: show }">
            <li><NuxtLink to="/"><span class="material-symbols-outlined">home</span>Home</NuxtLink></li>
            <li><NuxtLink to="table"><span class="material-symbols-outlined">table_bar</span>Mesas</NuxtLink></li>
            <li><NuxtLink to="product"><span class="material-symbols-outlined">grocery</span>Produtos</NuxtLink></li>
            <li><NuxtLink to="report"><span class="material-symbols-outlined">receipt_long</span>Relatorio</NuxtLink></li>
            <li><NuxtLink to="settings"><span class="material-symbols-outlined">manufacturing</span>Configurações</NuxtLink></li>
            <li v-if=principal?.isAdmin><a href="users"><span
                        class="material-symbols-outlined">person</span>Usuarios</a></li>
        </ul>
    </nav>
    <NuxtPage />
</template>

<script setup lang="ts">
const principal = usePrincipal();
const show = ref(false)
function show_menu() {
    show.value = !show.value;
}
</script>