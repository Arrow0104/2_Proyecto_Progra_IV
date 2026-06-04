// bolsa-empleo-fe/src/features/empresa/components/PuestoCard.jsx
import { useNavigate } from 'react-router-dom'
import { formatSalario } from '../../../shared/utils/formatters'

export default function PuestoCard({ puesto, onEliminar }) {
    const navigate = useNavigate()
    return (
        <div style={cardStyle}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start' }}>
                <div>
                    <h3 style={{ margin: 0 }}>{puesto.titulo}</h3>
                    <span style={badgeStyle(puesto.tipoPublicacion)}>{puesto.tipoPublicacion}</span>
                    <span style={{ ...badgeStyle(puesto.estado), marginLeft: '0.5rem' }}>{puesto.estado}</span>
                </div>
                <strong>{formatSalario(puesto.salario)}</strong>
            </div>
            <p style={{ color: '#555', margin: '0.5rem 0' }}>{puesto.descripcion}</p>
            <div style={{ display: 'flex', gap: '0.5rem', marginTop: '0.75rem' }}>
                <button onClick={() => navigate(`/empresa/puestos/${puesto.idPuesto}`)} style={btnSecondary}>Editar</button>
                <button onClick={() => onEliminar(puesto.idPuesto)} style={btnDanger}>Eliminar</button>
            </div>
        </div>
    )
}

const cardStyle = { background: 'var(--bg-card)', border: '1px solid var(--border)', borderRadius: 'var(--radius)', padding: '1rem', marginBottom: '1rem' }
const btnSecondary = { padding: '0.4rem 0.8rem', background: '#1e3a5f', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }
const btnDanger    = { padding: '0.4rem 0.8rem', background: '#c0392b', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }
function badgeStyle(val) {
    const colors = { PUBLICO: '#27ae60', PRIVADO: '#8e44ad', ACTIVO: '#2980b9', CERRADO: '#7f8c8d' }
    return { background: colors[val] || '#999', color: 'white', padding: '2px 8px', borderRadius: '12px', fontSize: '0.75rem' }
}