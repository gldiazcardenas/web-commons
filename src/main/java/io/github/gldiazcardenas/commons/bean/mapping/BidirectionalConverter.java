package io.github.gldiazcardenas.commons.bean.mapping;

/**
 * Converter that allows conversion in both directions, from source to target and vice versa.
 *
 * @param <SOURCE> The type of source.
 * @param <TARGET> The type of target.
 * @author Gabriel Diaz, 17/12/2020.
 */
public interface BidirectionalConverter<SOURCE, TARGET> extends Converter<SOURCE, TARGET> {

    SOURCE reverse(TARGET target);

}
