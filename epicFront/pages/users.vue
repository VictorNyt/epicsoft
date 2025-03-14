<script setup lang="ts">
definePageMeta({ middleware: 'auth' })
const principal = usePrincipal();
if (!principal.value?.isAdmin) {
    navigateTo("/");
}

</script>

<template>

</template>


<style scoped>
/*style scoped muda apenas essa pagina*/
</style>