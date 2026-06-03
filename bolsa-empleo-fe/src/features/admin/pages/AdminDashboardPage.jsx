// bolsa-empleo-fe/src/features/admin/pages/AdminDashboardPage.jsx
import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { adminService } from '../services/adminService'
import Loading from '../../../shared/components/Loading'

export default function AdminDashboardPage() {
    const [stats, setStats] = useState(null)

    useEffect(() => {
        Promise.all([adminService.getEmpresas(), adminService.getOferentes()])
            .then(([empresas, oferentes]) => setStats({ empresas: empresas.length, oferentes: oferentes.length }))
            .catch(console.error)
    }, [])

    if (!stats) return <Loading />

    return (
        <div>
            <h2>Panel de administración</h2>
            <div style={gridStyle}>
                <div style={cardStyle}>
                    <h3>🏢 Empresas</h3>
                    <p style={{ fontSize: '2rem', margin: 0 }}>{stats.empresas}</p>
                    <Link to="/admin/empresas" style={btnStyle}>Gestionar</Link>
                </div>
                <div style={cardStyle}>
                    <h3>👤 Oferentes</h3>
                    <p style={{ fontSize: '2rem', margin: 0 }}>{stats.oferentes}</p>
                    <Link to="/admin/oferentes" style={btnStyle}>Gestionar</Link>
                </div>
                <div style={cardStyle}>
                    <h3>⭐ Características</h3>
                    <Link to="/admin/caracteristicas" style={btnStyle}>Gestionar</Link>
                </div>
            </div>
        </div>
    )
}

const gridStyle = { display: 'grid', gridTemplateColumns: 'repeat(3,1fr)', gap: '1rem', marginTop: '1rem' }
const cardStyle = { border: '1px solid #ddd', borderRadius: '8px', padding: '1.5rem', background: 'white' }
const btnStyle  = { display: 'inline-block', marginTop: '0.75rem', padding: '0.5rem 1rem', background: '#1e3a5f', color: 'white', borderRadius: '4px', textDecoration: 'none' }