// bolsa-empleo-fe/src/features/admin/components/AprobacionCard.jsx
export default function AprobacionCard({ entidad, tipo, onActivar, onDesactivar }) {
    const nombre = tipo === 'empresa' ? entidad.nombre : `${entidad.nombre} ${entidad.apellido}`
    return (
        <div style={cardStyle}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <div>
                    <strong>{nombre}</strong>
                    <span style={{ marginLeft: '0.75rem', ...badgeStyle(entidad.active) }}>
            {entidad.active ? 'Activo' : 'Inactivo'}
          </span>
                </div>
                <div style={{ display: 'flex', gap: '0.5rem' }}>
                    {!entidad.active && <button onClick={() => onActivar(entidad[tipo === 'empresa' ? 'idEmpresa' : 'idOferente'])} style={btnActivar}>Activar</button>}
                    {entidad.active  && <button onClick={() => onDesactivar(entidad[tipo === 'empresa' ? 'idEmpresa' : 'idOferente'])} style={btnDesactivar}>Desactivar</button>}
                </div>
            </div>
            {tipo === 'empresa' && entidad.correoEmpresa && <p style={infoStyle}>✉ {entidad.correoEmpresa}</p>}
            {tipo === 'oferente' && entidad.correoOferente && <p style={infoStyle}>✉ {entidad.correoOferente}</p>}
        </div>
    )
}

const cardStyle = { background: 'var(--bg-card)', border: '1px solid var(--border)', borderRadius: 'var(--radius)', padding: '1rem', marginBottom: '0.75rem' }
const infoStyle = { margin: '0.25rem 0 0', color: 'var(--text-sec)', fontSize: '0.9rem' }
const btnActivar   = { padding: '0.4rem 0.8rem', background: '#27ae60', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }
const btnDesactivar= { padding: '0.4rem 0.8rem', background: '#c0392b', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }
function badgeStyle(activo) {
    return { padding: '2px 8px', borderRadius: '12px', fontSize: '0.75rem', background: activo ? '#27ae60' : '#e74c3c', color: 'white' }
}