// bolsa-empleo-fe/src/features/public/pages/HomePage.jsx
import { Link } from 'react-router-dom'
import { useAuth } from '../../auth/context/AuthContext'

export default function HomePage() {
    const { usuario } = useAuth()

    return (
        <div style={{ maxWidth: 800, margin: '3rem auto', textAlign: 'center' }}>
            <h1>Bolsa de Empleo</h1>
            <p style={{ fontSize: '1.2rem', color: '#555' }}>
                Encontrá tu próximo trabajo o el candidato ideal para tu empresa.
            </p>

            <div style={{ display: 'flex', justifyContent: 'center', gap: '1rem', marginTop: '2rem', flexWrap: 'wrap' }}>
                <Link to="/buscar" style={btnPrimary}>🔍 Ver puestos disponibles</Link>

                {!usuario && <>
                    <Link to="/login"              style={btnSecondary}>Iniciar sesión</Link>
                    <Link to="/registro/empresa"   style={btnSecondary}>Registrar empresa</Link>
                    <Link to="/registro/oferente"  style={btnSecondary}>Registrar como oferente</Link>
                </>}

                {usuario?.rol === 'EMPRESA'  && <Link to="/empresa"  style={btnPrimary}>Ir a mi panel</Link>}
                {usuario?.rol === 'OFERENTE' && <Link to="/oferente" style={btnPrimary}>Ir a mi panel</Link>}
                {usuario?.rol === 'ADMIN'    && <Link to="/admin"    style={btnPrimary}>Panel admin</Link>}
            </div>
        </div>
    )
}

const btnPrimary   = { padding: '0.75rem 1.5rem', background: '#1e3a5f', color: 'white', borderRadius: '6px', textDecoration: 'none', fontWeight: 'bold' }
const btnSecondary = { padding: '0.75rem 1.5rem', background: 'white', color: '#1e3a5f', borderRadius: '6px', textDecoration: 'none', border: '2px solid #1e3a5f' }