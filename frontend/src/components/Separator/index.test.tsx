import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Separator from './index';

jest.mock('./Separator.module.css', () => ({
    separator: 'separator',
}));

describe('Separator Component', () => {
    test('renders the separator div', () => {
        render(<Separator />);
        const separatorElement = screen.getByRole('separator');
        expect(separatorElement).toBeInTheDocument();
    });
});
