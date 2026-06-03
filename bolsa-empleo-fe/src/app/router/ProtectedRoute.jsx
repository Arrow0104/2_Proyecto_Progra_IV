// bolsa-empleo-fe/src/app/router/ProtectedRoute.jsx
import { Navigate, Outlet } from 'react-router-dom'
import { useAuth } from '../../features/auth/context/AuthContext'

export default function ProtectedRoute({ rol }) {
    const { usuario } = useAuth()
    if (!usuario) return <Navigate to="/login" replace />
    if (rol && usuario.rol !== rol) return <Navigate to="/acceso-denegado" replace />
    return <Outlet />
}