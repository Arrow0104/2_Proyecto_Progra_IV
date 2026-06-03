// bolsa-empleo-fe/src/app/router/AdminRoute.jsx
import ProtectedRoute from './ProtectedRoute'

export default function AdminRoute() {
    return <ProtectedRoute rol="ADMIN" />
}