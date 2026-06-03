// bolsa-empleo-fe/src/features/empresa/pages/EmpresaDashboardPage.jsx
import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { empresaService } from '../services/empresaService'
import Loading from '../../../shared/components/Loading'
import Alert from '../../../shared/components/Alert'

export default function EmpresaDashboardPage() {
    const [perfil, setPerfil] = useState(null)
    const [puestos, setPuestos] = useState([])
    const [error, setError] = useState('')

    useEffect(() => {
        Promise.all([empresaService.getPerfil(), empresaService.getMisPuestos()])
            .then(([p, ps]) => { setPerfil(p); setPuestos(ps) })
            .catch(e => setError(e.mensaje || 'Error cargando datos'))
    }, [])

    if (!perfil) return <Loading />

    return (
        <div>
            <h2>Bienvenida, {perfil.nombre}</h2>
            <Alert tipo="error" mensaje={error} />
            <div style={gridStyle}>
                <div style={cardStyle}>
                    <h3>📋 Mis puestos</h3>
                    <p style={{ fontSize: '2rem', margin: 0 }}>{puestos.length}</p>
                    <Link to="/empresa/puestos">Ver todos</Link>
                </div>
                <div style={cardStyle}>
                    <h3>➕ Nuevo puesto</h3>
                    <Link to="/empresa/puestos/nuevo" style={btnStyle}>Crear puesto</Link>
                </div>
                <div style={cardStyle}>
                    <h3>🔍 Buscar candidatos</h3>
                    <Link to="/empresa/candidatos" style={btnStyle}>Buscar</Link>
                </div>
            </div>
            <div style={{ marginTop: '2rem' }}>
                <h3>Perfil de empresa</h3>
                <p><strong>Teléfono:</strong> {perfil.telefono}</p>
                {perfil.localizacion    && <p><strong>Ubicación:</strong> {perfil.localizacion}</p>}
                {perfil.correoEmpresa   && <p><strong>Correo:</strong> {perfil.correoEmpresa}</p>}
                {perfil.descripcion     && <p><strong>Descripción:</strong> {perfil.descripcion}</p>}
                <Link to="/empresa/perfil/editar" style={{ color: '#1e3a5f' }}>Editar perfil</Link>
            </div>
        </div>
    )
}

const gridStyle = { display: 'grid', gridTemplateColumns: 'repeat(3,1fr)', gap: '1rem', marginTop: '1rem' }
const cardStyle = { border: '1px solid #ddd', borderRadius: '8px', padding: '1.5rem', background: 'white' }
const btnStyle  = { display: 'inline-block', padding: '0.5rem 1rem', background: '#1e3a5f', color: 'white', borderRadius: '4px', textDecoration: 'none' }