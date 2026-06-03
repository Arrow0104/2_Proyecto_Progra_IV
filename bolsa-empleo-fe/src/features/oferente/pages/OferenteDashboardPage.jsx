// bolsa-empleo-fe/src/features/oferente/pages/OferenteDashboardPage.jsx
import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { oferenteService } from '../services/oferenteService'
import Loading from '../../../shared/components/Loading'

export default function OferenteDashboardPage() {
    const [perfil, setPerfil] = useState(null)

    useEffect(() => {
        oferenteService.getPerfil().then(setPerfil).catch(console.error)
    }, [])

    if (!perfil) return <Loading />

    return (
        <div>
            <h2>Bienvenido, {perfil.nombre} {perfil.apellido}</h2>
            <div style={gridStyle}>
                <div style={cardStyle}>
                    <h3>👤 Mi perfil</h3>
                    <Link to="/oferente/perfil" style={btnStyle}>Ver perfil</Link>
                </div>
                <div style={cardStyle}>
                    <h3>⭐ Mis habilidades</h3>
                    <Link to="/oferente/habilidades" style={btnStyle}>Gestionar</Link>
                </div>
                <div style={cardStyle}>
                    <h3>📄 Mi CV</h3>
                    {perfil.cvPath
                        ? <><a href={perfil.cvPath} target="_blank" rel="noreferrer" style={{ color: '#1e3a5f' }}>Ver CV actual</a><br/></>
                        : <p style={{ color: '#888' }}>Sin CV</p>
                    }
                    <Link to="/oferente/cv" style={btnStyle}>Subir CV</Link>
                </div>
                <div style={cardStyle}>
                    <h3>🔍 Puestos disponibles</h3>
                    <Link to="/buscar" style={btnStyle}>Ver puestos</Link>
                </div>
            </div>
        </div>
    )
}

const gridStyle = { display: 'grid', gridTemplateColumns: 'repeat(2,1fr)', gap: '1rem', marginTop: '1rem' }
const cardStyle = { border: '1px solid #ddd', borderRadius: '8px', padding: '1.5rem', background: 'white' }
const btnStyle  = { display: 'inline-block', marginTop: '0.5rem', padding: '0.5rem 1rem', background: '#1e3a5f', color: 'white', borderRadius: '4px', textDecoration: 'none' }