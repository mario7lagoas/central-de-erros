package com.codenation.centralerrosapi.repository.log;

import com.codenation.centralerrosapi.model.Logs;
import com.codenation.centralerrosapi.model.User;
import com.codenation.centralerrosapi.model.enums.Level;
import com.codenation.centralerrosapi.repository.UserRepository;
import com.codenation.centralerrosapi.repository.filter.LogFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class LogRepositoryImpl implements LogRepositoryQuery {


    @PersistenceContext
    EntityManager manager;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Logs> filter(LogFilter logFilter, Pageable pageable) {

        From<?, ?> orderByFromEntity = null;

        System.out.println("ip local   -> " + request.getLocalAddr());
        System.out.println("ip Remoto -> " + request.getRemoteAddr());
        System.out.println("User      -> " + request.getRemoteUser());
        System.out.println("Sessao    -> " + request.getRequestedSessionId());

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Logs> criteria = builder.createQuery(Logs.class);

        Root<Logs> root = criteria.from(Logs.class);

        Predicate[] predicates = criarRestricted(logFilter, builder, root);
        criteria.where(predicates);

        if(logFilter.getOrderBy() == null){
            logFilter.setOrderBy(("date"));
        }

        if(logFilter.getOrderBy() != null){
            String nomePropriedadeOrdenacao = logFilter.getOrderBy();
            orderByFromEntity = root;

            if(logFilter.isAsc() && logFilter.getOrderBy() != null ) {
                criteria.orderBy(builder.asc(orderByFromEntity.get(nomePropriedadeOrdenacao)));
            }else{
                criteria.orderBy(builder.desc(orderByFromEntity.get(nomePropriedadeOrdenacao)));
            }

        }

        TypedQuery<Logs> query = manager.createQuery(criteria);

        additionalRestrictedDePaginate(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(logFilter));
    }

    private Long total(LogFilter logFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Logs> root = criteria.from(Logs.class);

        Predicate[] predicates = criarRestricted(logFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();

    }

    private void additionalRestrictedDePaginate(TypedQuery<Logs> query, Pageable pageable) {

        int pagingActual = pageable.getPageNumber();
        int totalDeRegistryForPaging = pageable.getPageSize();
        int primerRegistryDaPaging = pagingActual * totalDeRegistryForPaging;

        query.setFirstResult(primerRegistryDaPaging);
        query.setMaxResults(totalDeRegistryForPaging);

    }

    private Predicate[] criarRestricted(LogFilter logFilter, CriteriaBuilder builder, Root<Logs> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(logFilter.getDescricao())) {
            predicates.add(builder.like(
                    builder.lower(root.get("descricao")), "%" + logFilter.getDescricao()
                            .toLowerCase() + "%")
            );
        }


        if (logFilter.getLevels() != null && logFilter.getLevels().length > 0) {

            System.out.println("Entrou no level");

            List<Level> levels = new ArrayList<>();

            for (String level : logFilter.getLevels()) {
                System.out.println("[" + level + "]");
                levels.add(Level.valueOf(level));
            }
           predicates.add(root.get("level").in(levels));
        }

        User user = userRepository.findByEmail(request.getRemoteUser());
        predicates.add(builder.equal(root.get("user"), user.getId()));

        if(logFilter.getLevel() != null){
            predicates.add(builder.equal(root.get("level"), logFilter.getLevel()));
        }

        if (logFilter.getOrigem() != null) {
            predicates.add(builder.equal(root.get("origem"), logFilter.getOrigem()));
        }

        if (logFilter.getEnvents() != null) {
            predicates.add(builder.equal(root.get("qteEnvents"), logFilter.getEnvents()));
        }

        if (logFilter.getLogsDe() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("date"), logFilter.getLogsDe()));
        }

        if (logFilter.getLogsAte() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("createAt"), logFilter.getLogsDe()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);

    }
}
