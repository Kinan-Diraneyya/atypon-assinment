import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Ad from './index';

jest.mock('./Separator.module.css', () => ({
  separator: 'separator',
}));

describe('Separator Component', () => {
  test('renders the ad and its text', () => {
    render(<Ad />);
    const adElement = screen.getByRole('region');
    expect(adElement).toBeInTheDocument();
  });
});
