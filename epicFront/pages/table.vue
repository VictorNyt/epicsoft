<script setup lang="ts">
definePageMeta({ middleware: 'auth' })

const names = ref([{ name: 'asd' }, { name: 'asd' }, { name: 'asddsaadfasdasdfasdfasdfasdfasdfasdfasdfasdf' }, { name: 'asd' }, { name: 'asddsa' }]);
</script>

<template>
    <div class="naofunciona">
        <div class="mesas">
            <ul>
                <li v-for="name in names">{{ name }}</li>
            </ul>
        </div>
        <div class="mesainfo">
            <label for="a">a</label>
        </div>
    </div>
</template>

<style scoped>
.naofunciona {
    display: flex;
}

.mesainfo {
    border-radius: 2px;
    border-color: black;
    border-style: solid;
    min-width: 200px;
    width: 40%;
    height: 100vh;
    background-color: gray;
}

.mesas {
    color: aliceblue;
    z-index: 11;
    padding: 0px 0px 0px 155px;
    min-width: 250px;
    width: 50%;
    z-index: -5;
}

.mesas ul {
    gap: 20px;
}

.mesas ul li {
    list-style: none;
    border-radius: 2px;
    border-color: black;
    border-style: solid;
    height: 20%;
    margin: 5px;
    background-color: gray;
}

@media only screen and (max-width: 1024px) {
    .mesas {
        padding: 0px 0px 0px 25px;
    }
}

/*style scoped muda apenas essa pagina*/
</style>