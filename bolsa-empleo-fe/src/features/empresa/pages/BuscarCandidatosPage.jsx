// bolsa-empleo-fe/src/features/empresa/pages/BuscarCandidatosPage.jsx
import { useEffect, useState } from 'react'
import { api } from '../../../shared/api/apiClient'
import CandidatoCard from '../components/CandidatoCard'
import Loading from '../../../shared/components/Loading'
import Alert from '../../../shared/components/Alert'

export default function BuscarCandidatosPage() {
    const [oferentes, setOferentes] = useState([])
    const [filtro, setFiltro] = useState('')
    const [cargando, setCargando] = useState(true)
    const [error, setError] = useState('')

    useEffect(() => {
        api.get('/empresa/candidatos')
            .then(setOferentes)
            .catch(e => setError(e.mensaje || 'Error cargando candidatos'))
            .finally(() => setCargando(false))
    }, [])

    const filtrados = oferentes.filter(o =>
        `${o.nombre} ${o.apellido}`.toLowerCase().includes(filtro.toLowerCase())
    )

    if (cargando) return <Loading />

    return (
        <div>
            <h2>Buscar candidatos</h2>
            <Alert tipo="error" mensaje={error} />
            <input
                placeholder="Buscar por nombre..."
                value={filtro}
                onChange={e => setFiltro(e.target.value)}
                style={{ padding: '0.5rem', width: '100%', maxWidth: 400, marginBottom: '1rem', boxSizing: 'border-box' }}
            />
            {filtrados.length === 0
                ? <p>No se encontraron candidatos.</p>
                : filtrados.map(o => <CandidatoCard key={o.idOferente} oferente={o} />)
            }
        </div>
    )
}