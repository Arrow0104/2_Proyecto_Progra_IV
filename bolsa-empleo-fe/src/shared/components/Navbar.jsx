// bolsa-empleo-fe/src/shared/components/Navbar.jsx
import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../../features/auth/context/AuthContext'

export default function Navbar() {
    const { usuario, logout } = useAuth()
    const navigate = useNavigate()

    function handleLogout() {
        logout()
        navigate('/login')
    }

    return (
        <nav style={{ padding: '0.75rem 2rem', background: '#1e3a5f', display: 'flex', gap: '1rem', alignItems: 'center' }}>
            <Link to="/" style={{ color: 'white', fontWeight: 'bold', textDecoration: 'none' }}>Bolsa de Empleo</Link>
            <Link to="/buscar" style={{ color: '#cce' }}>Buscar puestos</Link>

            <div style={{ marginLeft: 'auto', display: 'flex', gap: '1rem' }}>
                {!usuario && <>
                    <Link to="/login" style={{ color: '#cce' }}>Iniciar sesión</Link>
                    <Link to="/registro/empresa" style={{ color: '#cce' }}>Registrar empresa</Link>
                    <Link to="/registro/oferente" style={{ color: '#cce' }}>Registrar oferente</Link>
                </>}

                {usuario?.rol === 'EMPRESA' && <>
                    <Link to="/empresa" style={{ color: '#cce' }}>Mi empresa</Link>
                    <Link to="/empresa/puestos" style={{ color: '#cce' }}>Puestos</Link>
                    <Link to="/empresa/candidatos" style={{ color: '#cce' }}>Candidatos</Link>
                </>}

                {usuario?.rol === 'OFERENTE' && <>
                    <Link to="/oferente" style={{ color: '#cce' }}>Dashboard</Link>
                    <Link to="/oferente/perfil" style={{ color: '#cce' }}>Perfil</Link>
                    <Link to="/oferente/habilidades" style={{ color: '#cce' }}>Habilidades</Link>
                </>}

                {usuario?.rol === 'ADMIN' && <>
                    <Link to="/admin" style={{ color: '#cce' }}>Admin</Link>
                    <Link to="/admin/empresas" style={{ color: '#cce' }}>Empresas</Link>
                    <Link to="/admin/oferentes" style={{ color: '#cce' }}>Oferentes</Link>
                    <Link to="/admin/caracteristicas" style={{ color: '#cce' }}>Características</Link>
                </>}

                {usuario && <button onClick={handleLogout} style={{ color: '#f99', background: 'none', border: 'none', cursor: 'pointer' }}>Salir</button>}
            </div>
        </nav>
    )
}