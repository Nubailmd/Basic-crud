<template>
  <div>
    <h2>Users</h2>

    <input v-model="name" placeholder="Name" />
    <input v-model="email" placeholder="Email" />
    <button v-if="!editingId" @click="createUser">Add User</button>
    <button v-else @click="updateUser">Update User</button>

    <ul>
      <li v-for="user in users" :key="user.id">
        {{ user.name }} - {{ user.email }}
        <button @click="editUser(user)">Edit</button>
        <button @click="deleteUser(user.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { api } from './api'

const users = ref([])
const name = ref('')
const email = ref('')
const editingId = ref(null)

const fetchUsers = async () => {
  const res = await api.get('/users')
  users.value = res.data
}

const editUser = (user) => {
  name.value = user.name
  email.value = user.email
  editingId.value = user.id
}
const createUser = async () => {
  if (!name.value || !email.value) return
  await api.post('/users', { name: name.value, email: email.value })
  name.value = ''
  email.value = ''
  fetchUsers()
}

const deleteUser = async (id) => {
  await api.delete(`/users/${id}`)
  fetchUsers()
}
const updateUser = async () => {
  if (!name.value || !email.value || !editingId.value) return

  await api.put('/users/update', {
    id: editingId.value,
    name: name.value,
    email: email.value,
  })

  name.value = ''
  email.value = ''
  editingId.value = null
  fetchUsers()
}

onMounted(fetchUsers)
</script>

<style>
body {
  font-family: Arial, sans-serif;
  margin: 20px;
}
input {
  margin: 0 5px 10px 0;
}
</style>
