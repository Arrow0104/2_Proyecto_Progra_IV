// bolsa-empleo-fe/src/shared/api/apiClient.js
const BASE = '/api'

function getToken() {
    return localStorage.getItem('token')
}

async function request(method, path, body = null) {
    const headers = { 'Content-Type': 'application/json' }
    const token = getToken()
    if (token) headers['Authorization'] = `Bearer ${token}`

    const res = await fetch(`${BASE}${path}`, {
        method,
        headers,
        body: body ? JSON.stringify(body) : null
    })

    if (!res.ok) {
        const err = await res.json().catch(() => ({ mensaje: res.statusText }))
        throw { status: res.status, mensaje: err.mensaje || 'Error desconocido' }
    }

    if (res.status === 204) return null
    return res.json()
}

export const api = {
    get:    (path)         => request('GET',    path),
    post:   (path, body)   => request('POST',   path, body),
    put:    (path, body)   => request('PUT',    path, body),
    delete: (path)         => request('DELETE', path)
}