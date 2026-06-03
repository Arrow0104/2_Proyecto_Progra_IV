// bolsa-empleo-fe/src/features/auth/context/AuthContext.jsx
import { createContext, useContext, useState, useEffect } from 'react'
import { storage } from '../utils/storage'
import { isTokenExpired } from '../utils/jwtUtils'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
    const [usuario, setUsuario] = useState(null)
    const [cargando, setCargando] = useState(true)

    useEffect(() => {
        const token = storage.getToken()
        if (token && !isTokenExpired(token)) {
            setUsuario({ token, rol: storage.getRol(), usuarioId: storage.getUsuarioId() })
        } else {
            storage.clear()
        }
        setCargando(false)
    }, [])

    function login(token, rol, usuarioId) {
        storage.saveSession(token, rol, usuarioId)
        setUsuario({ token, rol, usuarioId })
    }

    function logout() {
        storage.clear()
        setUsuario(null)
    }

    return (
        <AuthContext.Provider value={{ usuario, login, logout, cargando }}>
            {children}
        </AuthContext.Provider>
    )
}

export function useAuth() {
    return useContext(AuthContext)
}