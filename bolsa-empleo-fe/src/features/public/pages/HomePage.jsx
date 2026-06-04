import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { useAuth } from '../../auth/context/AuthContext'
import { publicService } from '../services/publicService'
import { formatSalario } from '../../../shared/utils/formatters'

export default function HomePage() {
    const { usuario } = useAuth()
    const [puestos, setPuestos] = useState([])

    useEffect(() => {
        publicService.getPuestosPublicos('')
            .then(data => setPuestos(data.slice(0, 5)))
            .catch(console.error)
    }, [])

    return (
        <div>
            <div className="hero">
                <h1>Bolsa de Empleo</h1>
                <p>Encontrá tu próximo trabajo o el candidato ideal para tu empresa.</p>
                <div style={{ display: 'flex', gap: '1rem', marginTop: '1.5rem', flexWrap: 'wrap' }}>
                    <Link to="/buscar" className="btn btn-primary">🔍 Ver todos los puestos</Link>
                    {!usuario && <>
                        <Link to="/login" className="btn btn-outline">Iniciar sesión</Link>
                        <Link to="/registro/empresa" className="btn btn-outline">Registrar empresa</Link>
                        <Link to="/registro/oferente" className="btn btn-outline">Registrar oferente</Link>
                    </>}
                    {usuario?.rol === 'EMPRESA'  && <Link to="/empresa"  className="btn btn-primary">Ir a mi panel</Link>}
                    {usuario?.rol === 'OFERENTE' && <Link to="/oferente" className="btn btn-primary">Ir a mi panel</Link>}
                    {usuario?.rol === 'ADMIN'    && <Link to="/admin"    className="btn btn-primary">Panel admin</Link>}
                </div>
            </div>

            <h2 style={{ marginBottom: '1rem' }}>Puestos recién publicados</h2>
            <div className="card-grid">
                {puestos.map(p => (
                    <div key={p.idPuesto} className="puesto-card-wrapper">
                        <div className="card">
                            <div className="card-title">{p.titulo}</div>
                            <div className="card-subtitle">
                                {formatSalario(p.salario)}
                            </div>
                            <Link to="/buscar" className="btn btn-outline btn-sm">Ver detalle</Link>
                        </div>
                        <div className="puesto-tooltip">
                            <strong>Requisitos del puesto</strong>
                            <p style={{ marginTop: '0.4rem', fontSize: '0.83rem' }}>{p.descripcion}</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
}