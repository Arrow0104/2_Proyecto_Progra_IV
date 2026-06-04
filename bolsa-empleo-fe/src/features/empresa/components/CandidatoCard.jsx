// bolsa-empleo-fe/src/features/empresa/components/CandidatoCard.jsx
export default function CandidatoCard({ oferente }) {
    return (
        <div style={cardStyle}>
            <h3 style={{ margin: 0 }}>{oferente.nombre} {oferente.apellido}</h3>
            <p style={{ color: '#555', margin: '0.25rem 0' }}>
                {oferente.nacionalidad} · {oferente.residencia}
            </p>
            {oferente.correoOferente && <p style={{ margin: '0.25rem 0' }}>✉ {oferente.correoOferente}</p>}
            {oferente.telefono        && <p style={{ margin: '0.25rem 0' }}>📞 {oferente.telefono}</p>}
            {oferente.cvPath && (
                <a href={oferente.cvPath} target="_blank" rel="noreferrer"
                   style={{ color: '#1e3a5f' }}>Ver CV</a>
            )}
        </div>
    )
}

const cardStyle = { background: 'var(--bg-card)', border: '1px solid var(--border)', borderRadius: 'var(--radius)', padding: '1rem', marginBottom: '1rem' }